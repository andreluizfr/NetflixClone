package com.example.NetflixClone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.NetflixClone.Models.Movie;
import com.example.NetflixClone.Models.enums.Genre;
import com.example.NetflixClone.Repositories.MovieRepositoryDAO;

@SpringBootApplication
public class NetflixCloneApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(NetflixCloneApplication.class, args);
	}

	@Autowired
	private MovieRepositoryDAO movieRepository;

	@Override
	public void run(String... args) throws Exception {

		List<Genre> genres = new ArrayList<Genre>(Arrays.asList(Genre.ROMANCE));
		List<String> actorsActresses = new ArrayList<String>(Arrays.asList("Josephine Langford", "Hero Fiennes-Tiffin"));
		Movie movie1 = new Movie(
			"After - Depois da Promessa",
			false,
			genres,
			"Castille Landon",
			2022,
			"Enquanto Hardin confronta seu passado e Tessa sofre uma tragédia, os dois devem decidir se continuam seu relacionamento apaixonado, mas tóxico, ou fazem mudanças dramáticas em suas vidas.",
			16,
			"https://www.claquete.com/fotos/filmes/poster/14124_medio.jpg",
			"|GAT1d?wXnIUMdiwEhsmada0RPjEs.xZs:SNsos.4nDii_xu%MxtxEWWkCtl%2ofaKNGRkwdS2WBXTxaxFjFR*S3s.oLWVW;j[WWWWbHoLoLWqa|s:bHS2WBWBR*R*WooLIoM{fjozozofS2aybHoLW;a{S2Wqs.X8bHWV",
			true,
			4,
			actorsActresses
		);
		this.movieRepository.save(movie1);

		genres = new ArrayList<Genre>(Arrays.asList(Genre.COMEDY, Genre.MYSTERY));
		actorsActresses = new ArrayList<String>(Arrays.asList("Adam Sandler", "Allen Covert", "Tripp Vinson", "Jennifer Aniston", "James Vanderbilt", "James D. Stern"));
		Movie movie2 = new Movie(
			"Mistério em Paris",
			false,
			genres,
			"Jeremy Garelick",
			2023,
			"Nick e Audrey Spitz abrem uma agência de investigação e finalmente conseguem um caso importante: um amigo bilionário é sequestrado no dia de seu casamento.",
			14,
			"https://br.web.img2.acsta.net/c_310_420/pictures/23/04/03/16/27/2188262.jpg",
			"|GAT1d?wXnIUMdiwEhsmada0RPjEs.xZs:SNsos.4nDii_xu%MxtxEWWkCtl%2ofaKNGRkwdS2WBXTxaxFjFR*S3s.oLWVW;j[WWWWbHoLoLWqa|s:bHS2WBWBR*R*WooLIoM{fjozozofS2aybHoLW;a{S2Wqs.X8bHWV",
			false,
			1,
			actorsActresses
		);
		this.movieRepository.save(movie2);

		genres = new ArrayList<Genre>(Arrays.asList(Genre.ACTION, Genre.THRILLER));
		actorsActresses = new ArrayList<String>(Arrays.asList());
		Movie movie3 = new Movie(
			"Resgate 2",
			false,
			genres,
			"Sam Hargrave",
			2023,
			"Depois de escapar da morte por um triz, o mercenário Tyler Rake encara mais uma missão perigosa: resgatar a família de um criminoso implacável.",
			16,
			"https://www.atoupeira.com.br/wp-content/uploads/2023/05/resgate-2-poster-nacional-2.jpg",
			"|GAT1d?wXnIUMdiwEhsmada0RPjEs.xZs:SNsos.4nDii_xu%MxtxEWWkCtl%2ofaKNGRkwdS2WBXTxaxFjFR*S3s.oLWVW;j[WWWWbHoLoLWqa|s:bHS2WBWBR*R*WooLIoM{fjozozofS2aybHoLW;a{S2Wqs.X8bHWV",
			true,
			2,
			actorsActresses
		);
		this.movieRepository.save(movie3);

		Movie movie4 = new Movie(
			"Resgate 2",
			false,
			genres,
			"Sam Hargrave",
			2023,
			"Depois de escapar da morte por um triz, o mercenário Tyler Rake encara mais uma missão perigosa: resgatar a família de um criminoso implacável.",
			16,
			"https://www.atoupeira.com.br/wp-content/uploads/2023/05/resgate-2-poster-nacional-2.jpg",
			"|GAT1d?wXnIUMdiwEhsmada0RPjEs.xZs:SNsos.4nDii_xu%MxtxEWWkCtl%2ofaKNGRkwdS2WBXTxaxFjFR*S3s.oLWVW;j[WWWWbHoLoLWqa|s:bHS2WBWBR*R*WooLIoM{fjozozofS2aybHoLW;a{S2Wqs.X8bHWV",
			true,
			2,
			actorsActresses
		);
		this.movieRepository.save(movie4);

		Movie movie5 = new Movie(
			"Resgate 2",
			false,
			genres,
			"Sam Hargrave",
			2023,
			"Depois de escapar da morte por um triz, o mercenário Tyler Rake encara mais uma missão perigosa: resgatar a família de um criminoso implacável.",
			16,
			"https://www.atoupeira.com.br/wp-content/uploads/2023/05/resgate-2-poster-nacional-2.jpg",
			"|GAT1d?wXnIUMdiwEhsmada0RPjEs.xZs:SNsos.4nDii_xu%MxtxEWWkCtl%2ofaKNGRkwdS2WBXTxaxFjFR*S3s.oLWVW;j[WWWWbHoLoLWqa|s:bHS2WBWBR*R*WooLIoM{fjozozofS2aybHoLW;a{S2Wqs.X8bHWV",
			true,
			2,
			actorsActresses
		);
		this.movieRepository.save(movie5);

		Movie movie6 = new Movie(
			"Resgate 2",
			false,
			genres,
			"Sam Hargrave",
			2023,
			"Depois de escapar da morte por um triz, o mercenário Tyler Rake encara mais uma missão perigosa: resgatar a família de um criminoso implacável.",
			16,
			"https://www.atoupeira.com.br/wp-content/uploads/2023/05/resgate-2-poster-nacional-2.jpg",
			"|GAT1d?wXnIUMdiwEhsmada0RPjEs.xZs:SNsos.4nDii_xu%MxtxEWWkCtl%2ofaKNGRkwdS2WBXTxaxFjFR*S3s.oLWVW;j[WWWWbHoLoLWqa|s:bHS2WBWBR*R*WooLIoM{fjozozofS2aybHoLW;a{S2Wqs.X8bHWV",
			true,
			2,
			actorsActresses
		);
		this.movieRepository.save(movie6);

		Movie movie7 = new Movie(
			"Resgate 2",
			false,
			genres,
			"Sam Hargrave",
			2023,
			"Depois de escapar da morte por um triz, o mercenário Tyler Rake encara mais uma missão perigosa: resgatar a família de um criminoso implacável.",
			16,
			"https://www.atoupeira.com.br/wp-content/uploads/2023/05/resgate-2-poster-nacional-2.jpg",
			"|GAT1d?wXnIUMdiwEhsmada0RPjEs.xZs:SNsos.4nDii_xu%MxtxEWWkCtl%2ofaKNGRkwdS2WBXTxaxFjFR*S3s.oLWVW;j[WWWWbHoLoLWqa|s:bHS2WBWBR*R*WooLIoM{fjozozofS2aybHoLW;a{S2Wqs.X8bHWV",
			true,
			2,
			actorsActresses
		);
		this.movieRepository.save(movie7);

	}
}
