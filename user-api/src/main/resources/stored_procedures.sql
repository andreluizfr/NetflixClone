------------------------------------------ STORED PROCEDURES ---------------------------------------------------------

CREATE OR REPLACE FUNCTION public.create_partition_or_insert ()
RETURNS trigger
LANGUAGE plpgsql
VOLATILE 
STRICT
SECURITY DEFINER
COST 100
AS $$
   DECLARE
       partition_date TEXT;
       current_partition TEXT;
       index_def TEXT;
   BEGIN
       partition_date := to_char(NEW.created_at,'YYYY_MM');
       current_partition := TG_TABLE_NAME || '_' || partition_date;
       IF NOT EXISTS(SELECT relname FROM pg_class WHERE relname=current_partition) THEN
           RAISE NOTICE 'A partition has been created %',current_partition;
           EXECUTE 'CREATE TABLE IF NOT EXISTS ' || current_partition ||  ' (check (to_char(created_at::timestamp::date,''YYYY_MM'') = ''' || to_char(NEW.created_at::timestamp::date,'YYYY_MM') || ''')) INHERITS (' || TG_TABLE_NAME || ');'
                   ' GRANT ALL PRIVILEGES ON ' || current_partition || ' TO postgres;';

           FOR index_def IN
               SELECT REPLACE(indexdef, TG_TABLE_NAME, current_partition) FROM pg_indexes WHERE tablename = TG_TABLE_NAME
           LOOP
               EXECUTE index_def || ';';
           END LOOP;
       END IF;
       EXECUTE 'INSERT INTO ' || current_partition || ' SELECT(' || TG_TABLE_NAME || ' ' || quote_literal(NEW) || ').* ON CONFLICT DO NOTHING RETURNING id;';
       RETURN NULL;
       EXCEPTION
       WHEN duplicate_table THEN
           IF EXISTS(SELECT relname FROM pg_class WHERE relname=current_partition) THEN
               EXECUTE 'INSERT INTO ' || current_partition || ' SELECT(' || TG_TABLE_NAME || ' ' || quote_literal(NEW) || ').* ON CONFLICT DO NOTHING RETURNING id;';
           END IF;
       RETURN NULL;
   END;
$$;

CREATE OR REPLACE TRIGGER user_activity_insert
BEFORE INSERT ON public.user_activity
FOR EACH ROW EXECUTE PROCEDURE create_partition_or_insert();

CREATE OR REPLACE FUNCTION public.user_activity_remove_partitions()
RETURNS void
LANGUAGE plpgsql
COST 100
VOLATILE NOT LEAKPROOF
AS $$
   DECLARE 
   tabela_pai text := 'user_activity';
   particao text;
   particao_vazia boolean;
   BEGIN
       FOR particao IN (SELECT tab_filha.relname
                       FROM pg_inherits AS assoc_heranca
                       INNER JOIN pg_class AS tab_pai ON tab_pai.oid = assoc_heranca.inhparent
                       INNER JOIN pg_class AS tab_filha ON tab_filha.oid = assoc_heranca.inhrelid
                       WHERE tab_pai.relname = tabela_pai
                       ORDER BY tab_filha.relname ASC)
       LOOP 
           particao_vazia := NULL;
           EXECUTE format('SELECT NOT EXISTS(SELECT 1 FROM %I LIMIT 1);', particao) 
           INTO particao_vazia;
           IF (particao_vazia IS true) THEN
               EXECUTE format('DROP TABLE IF EXISTS %I;', particao);
               RAISE NOTICE 'Partição removida: %', particao;
           END IF;
       END LOOP;
   END;
$$;
GRANT EXECUTE ON FUNCTION public.user_activity_remove_partitions() TO postgres;