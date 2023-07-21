package com.example.NetflixClone.BD;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.NetflixClone.Models.Account;
import com.example.NetflixClone.Models.Anime;
import com.example.NetflixClone.Models.Episode;
import com.example.NetflixClone.Models.Media;
import com.example.NetflixClone.Models.MediaList;
import com.example.NetflixClone.Models.Movie;
import com.example.NetflixClone.Models.User;
import com.example.NetflixClone.Models.UserDTO;
import com.example.NetflixClone.Models.enums.Genre;
import com.example.NetflixClone.Repositories.AccountRepositoryDAO;
import com.example.NetflixClone.Repositories.AnimeRepositoryDAO;
import com.example.NetflixClone.Repositories.EpisodeRepositoryDAO;
import com.example.NetflixClone.Repositories.MediaListRepositoryDAO;
import com.example.NetflixClone.Repositories.MovieRepositoryDAO;
import com.example.NetflixClone.Repositories.TvShowRepositoryDAO;
import com.example.NetflixClone.Repositories.UserRepositoryDAO;

@Service
public class InitialQueries {

    @Autowired
	private AccountRepositoryDAO accountRepository;

	@Autowired
	private UserRepositoryDAO userRepository;

	@Autowired
	private EpisodeRepositoryDAO episodeRepository;

	@Autowired
	private MovieRepositoryDAO movieRepository;

	@Autowired
	private AnimeRepositoryDAO animeRepository;

	@Autowired
	private TvShowRepositoryDAO tvShowRepository;

	@Autowired
	private MediaListRepositoryDAO mediaListRepository;
    
    public void populateUsers() {

		System.out.println("populating users table...");

        Account account = new Account();
		Account newAccount = this.accountRepository.save(account);

		UserDTO userDTO = new UserDTO("andre@email.com", "andre123", "1999-07-22", null);
		User user = new User(userDTO);
		this.userRepository.save(user);
    }

    public void populateMoviesAndLists() {

		System.out.println("populating movies, animes and tvShows table, mediaLists table...");
	/*
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
			"https://www.claquete.com.br/fotos/filmes/poster/14124_medio.jpg",
			"https://cdn.fstatic.com/media/movies/covers/2022/02/305140_background.jpg",
			"https://www.youtube.com/watch?v=us7zUFtoc9Q",
			true,
			4,
			actorsActresses,
			new Episode(
				"https://www.youtube.com/watch?v=us7zUFtoc9Q",
				100
			)
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
			"https://p2.trrsf.com/image/fget/cf/774/0/images.terra.com/2023/03/31/1844513309-77cce32034f8e41609274fff44185dfd.jpg",
			"https://www.kakuchopurei.com/wp-content/uploads/2023/03/MURDER-MYSTERY-2-_-Key-Art-1.jpg",
			"https://www.youtube.com/watch?v=FDcsfr7bsE8",
			false,
			1,
			actorsActresses,
			new Episode(
				"https://www.youtube.com/watch?v=FDcsfr7bsE8",
				100
			)
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
			"https://s2-techtudo.glbimg.com/PyUKS3XAYVrUpD9jGC5443dpz_k=/0x0:1045x551/924x0/smart/filters:strip_icc()/i.s3.glbimg.com/v1/AUTH_08fbf48bc0524877943fe86e43087e7a/internal_photos/bs/2023/X/a/ogKNjNSmiC2H2o9duV1w/aaaaqwqo-fc4mwa5e-63h4jpgu8zfwvbaajgfok-odqaljok9nscre8bf20cpdsd-x8dizihmtxlyrxgmf-oh8ukdg0jwiyyjstd7qlq7tkpw-bmyqhmlahsg-7ayaoj3h5iult9psj4vhqbyg-703tj-sua.jpg",
			"https://www.ofuxico.com.br/wp-content/uploads/2023/05/chris-hemsworth-poster-resgate-2-netflix.jpg",
			"https://www.youtube.com/watch?v=7ZzP1vgk5nA",
			true,
			2,
			actorsActresses,
			new Episode(
				"https://www.youtube.com/watch?v=7ZzP1vgk5nA",
				100
			)
		);
		this.movieRepository.save(movie3);

		List<String> voiceActorsActresses = new ArrayList<>(Arrays.asList("Takahiro Sakurai", "Natsuki Hanae", "Akari Kit\u014D, Kana Hanazawa"));
		Episode episode1 = new Episode(
			"Crueldade", 
			"https://occ-0-3936-3851.1.nflxso.net/dnm/api/v6/9pS1daC2n6UGc3dUogvWIPMR_OU/AAAABbIG3xyrM5-FAxBYRTHz0HckVeMGheZteiWRjFXN82hawuGEkvTGogYVmTeiIHS7bYEfSzP-YRMABt-BEwSfLSH7zBJWy2KnKSWHZrkpgV01QC4l-PAQX4Rk.jpg?r=0ec", 
			"https://www.youtube.com/watch?v=VQGCKyvzIM4", 
			24,
			1
		);
		episode1 = this.episodeRepository.save(episode1);

		Episode episode2 = new Episode(
			"Sakonji Urokodaki, o treinador", 
			"https://occ-0-3936-3851.1.nflxso.net/dnm/api/v6/9pS1daC2n6UGc3dUogvWIPMR_OU/AAAABSi7WTW1BKzTg_-XuOdfdxxbyDsQYNWl4a8F-tIy05zcXDMNF-z7vHBRhEamJkM8c7BHpPxcA8DzWnTOboe08SuT6J9aVdgV2TdIejHHsmWtBvVjDG_s0wNU.jpg?r=584", 
			"https://www.youtube.com/watch?v=VQGCKyvzIM4", 
			24,
			2
		);
		episode2 = this.episodeRepository.save(episode2);

		Episode episode3 = new Episode(
			"Sabito e Makomo", 
			"https://occ-0-3936-3851.1.nflxso.net/dnm/api/v6/9pS1daC2n6UGc3dUogvWIPMR_OU/AAAABWzDzcspEWJlZSQltan5qtG3BGlMv54cV7gBiPzSAjikL1O-LWIy-5lp_IMR3R4iYSwTZWtragcztGd-ARMdOodZWPOgjmpmyqRBBATobQy2CELcRfIXN6up.jpg?r=295", 
			"https://www.youtube.com/watch?v=VQGCKyvzIM4", 
			24,
			3
		);
		episode3 = this.episodeRepository.save(episode3);

		Episode episode4 = new Episode(
			"Seleção final", 
			"https://occ-0-3936-3851.1.nflxso.net/dnm/api/v6/9pS1daC2n6UGc3dUogvWIPMR_OU/AAAABdGByJ3U6edMdUtCGfshJGM390ZrDGsYEmBcE4o_b1iLOZFS5D863_rSV27oM_A9GdYeU_gSbW07R_Y_uIyc6672IwkKhnUFelhLWd7rplt1NuhtfQZvpO4Y.jpg?r=5b6", 
			"https://www.youtube.com/watch?v=VQGCKyvzIM4", 
			24,
			4
		);
		episode4 = this.episodeRepository.save(episode4);

		Episode episode5 = new Episode(
			"Meu próprio aço", 
			"https://occ-0-3936-3851.1.nflxso.net/dnm/api/v6/9pS1daC2n6UGc3dUogvWIPMR_OU/AAAABTuCttRHtc_SATCV5Ttb9fRKEGi_G9-MAvbHv-trwk65uwbTebp4dPr_vlznB1WmlLRSXesIULexNZMnW3ewrRkNXgTq1MINIJcUQWNSr8FgZRkOICusxWay.jpg?r=6d0", 
			"https://www.youtube.com/watch?v=VQGCKyvzIM4", 
			24,
			5
		);
		episode5 = this.episodeRepository.save(episode5);

		Episode episode6 = new Episode(
			"O espadachim que acompanhou um demônio", 
			"https://occ-0-3936-3851.1.nflxso.net/dnm/api/v6/9pS1daC2n6UGc3dUogvWIPMR_OU/AAAABUjr3zo2fECvIR8VtUaye_ZvHRgLdZ1_YFh4ch1izr12iM7-7HFvM0BZryhxQwxXHe3wHLc03exlwmMaUFnSEsZy7m6GjRbyWeNR5-qzixO9834uPLvEP2Y2.jpg?r=333", 
			"https://www.youtube.com/watch?v=VQGCKyvzIM4", 
			24,
			5
		);
		episode6 = this.episodeRepository.save(episode6);

		Episode episode7 = new Episode(
			"Muzan Kibutsuji", 
			"https://occ-0-3936-3851.1.nflxso.net/dnm/api/v6/9pS1daC2n6UGc3dUogvWIPMR_OU/AAAABbFKMspQ03NmBCbvflwaxzTYOGTd7ontmfY1sS5of1HSf16c1V-U-qr4hrJBZSpGOtudbsbdKbfaLXjR21O0JFwtTy2h3orE0mHMS4MSnN_konc29FB6DL8w.jpg?r=9d7", 
			"https://www.youtube.com/watch?v=VQGCKyvzIM4", 
			24,
			7
		);
		episode7 = this.episodeRepository.save(episode7);

		Episode episode8 = new Episode(
			"O aroma do sangue encantado", 
			"https://occ-0-3936-3851.1.nflxso.net/dnm/api/v6/9pS1daC2n6UGc3dUogvWIPMR_OU/AAAABdU6p-T5SvHXOWeXtnr7SHf-RbB7OxttXoQXCHwwtodWwrTtlHHfUhRu14B_pM-6J0-jw0oHRqi4QKpeYBJz-GkjEVOjPnjuQHkfS6cBTdt7ywKrSpQwdAXd.jpg?r=df0", 
			"https://www.youtube.com/watch?v=VQGCKyvzIM4", 
			24,
			8
		);
		episode8 = this.episodeRepository.save(episode8);

		List<Episode> episodes = new ArrayList<>(Arrays.asList(episode1, episode2, episode3, episode4, episode5, episode6, episode7, episode8));
		Anime anime1 = new Anime(
			"Demon Slayer",
			true,
			genres,
			"Koyoharu Gotouge",
			2019,
			"A hist\u00F3ria conta sobre um jovem que ganha a vida vendendo carv\u00E3o at\u00E9 descobrir que sua fam\u00EDlia foi massacrada por um dem\u00F4nio, restando apenas sua irm\u00E3, Nezuko, que foi infectada e est\u00E1 se tornando um monstro. O protagonista, ent\u00E3o, decide virar um ca\u00E7ador de dem\u00F4nios e buscar vingan\u00E7a.",
			16,
			"https://animerant.com.br/wp-content/uploads/2023/02/demon-slayer-ordem-3.jpg",
			"https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEgEP35guHXTOFmNq4Xpug0dNOlU5_lrSDSqXU5YisCpj5iSoDKgYpyo_jJ9b0Gej6Q_uFTSGbXGs8KlQZCcllq5atDR3-7_ycWq8CvSdt7-UDAhb6rIa8CI3IfS3jfSBLhJMvzrKaE-iSYb5FP4vcKCi3m3cfAr5ZzTezTtNFJF8Tru2B1HxRCLrnQ3LQ/s2233/demon-slayer-hinokami-chronicles-capa.jpg",
			"https://www.youtube.com/watch?v=VQGCKyvzIM4",
			2,
			1,
			"Ufotable",
			voiceActorsActresses,
			episodes
		);
		this.animeRepository.save(anime1);


		Set<Media> mediaList = new HashSet<>(Arrays.asList(
			(Media) movie1,
		 	(Media) movie2, 
			(Media) movie3, 
			(Media) anime1,
			(Media) movie1,
		 	(Media) movie2, 
			(Media) movie3, 
			(Media) anime1 
		));

		MediaList media1List = new MediaList("Lista de teste1", mediaList);
		this.mediaListRepository.save(media1List);

		MediaList mediaList2 = new MediaList("Lista de teste2", mediaList);
		this.mediaListRepository.save(mediaList2);

		MediaList mediaList3 = new MediaList("Lista de teste3", mediaList);
		this.mediaListRepository.save(mediaList3);

		MediaList mediaList4 = new MediaList("Lista de teste4", mediaList);
		this.mediaListRepository.save(mediaList4);

		MediaList mediaList5 = new MediaList("Lista de teste5", mediaList);
		this.mediaListRepository.save(mediaList5);

		MediaList mediaList6 = new MediaList("Lista de teste6", mediaList);
		this.mediaListRepository.save(mediaList6);

		MediaList mediaList7 = new MediaList("Lista de teste7", mediaList);
		this.mediaListRepository.save(mediaList7);

		MediaList mediaList8 = new MediaList("Lista de teste8", mediaList);
		this.mediaListRepository.save(mediaList8);

		 */
    }

}
