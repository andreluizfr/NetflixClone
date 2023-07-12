package com.example.NetflixClone.BD;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.NetflixClone.Models.Account;
import com.example.NetflixClone.Models.Media;
import com.example.NetflixClone.Models.MediaList;
import com.example.NetflixClone.Models.Movie;
import com.example.NetflixClone.Models.User;
import com.example.NetflixClone.Models.UserDTO;
import com.example.NetflixClone.Models.enums.Genre;
import com.example.NetflixClone.Repositories.AccountRepositoryDAO;
import com.example.NetflixClone.Repositories.MediaListRepositoryDAO;
import com.example.NetflixClone.Repositories.MovieRepositoryDAO;
import com.example.NetflixClone.Repositories.UserRepositoryDAO;

@Service
public class InitialQueries {

    @Autowired
	private AccountRepositoryDAO accountRepository;

	@Autowired
	private UserRepositoryDAO userRepository;

	@Autowired
	private MovieRepositoryDAO movieRepository;

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

		System.out.println("populating movies table and mediaLists table...");
        
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

		Set<Media> mediasList = new HashSet<>(Arrays.asList(
			(Media) movie1,
		 	(Media) movie2, 
			(Media) movie3, 
			(Media) movie3, 
			(Media) movie4, 
			(Media) movie5, 
			(Media) movie6,
			(Media) movie7));

		MediaList mediaList = new MediaList("Lista de teste1", mediasList);
		this.mediaListRepository.save(mediaList);

		MediaList mediaList2 = new MediaList("Lista de teste2", mediasList);
		this.mediaListRepository.save(mediaList2);

		MediaList mediaList3 = new MediaList("Lista de teste3", mediasList);
		this.mediaListRepository.save(mediaList3);

		MediaList mediaList4 = new MediaList("Lista de teste4", mediasList);
		this.mediaListRepository.save(mediaList4);

		MediaList mediaList5 = new MediaList("Lista de teste5", mediasList);
		this.mediaListRepository.save(mediaList5);

		MediaList mediaList6 = new MediaList("Lista de teste6", mediasList);
		this.mediaListRepository.save(mediaList6);

		MediaList mediaList7 = new MediaList("Lista de teste7", mediasList);
		this.mediaListRepository.save(mediaList7);

		MediaList mediaList8 = new MediaList("Lista de teste8", mediasList);
		this.mediaListRepository.save(mediaList8);
    }

}
