package com.tonearena.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.tonearena.model.Song;
import com.tonearena.repositories.SongRepo;

@Component
public class SongFormValidator implements Validator{
	
		@Autowired
		SongRepo songRepo;
		
		@Override
		public boolean supports(Class<?> clazz) {
			return Song.class.equals(clazz);
		}

		@Override
		public void validate(Object target, Errors errors) {

			Song song = (Song) target;
			
			if(songRepo.findOne(song.getSongId())!=null){
				errors.rejectValue("songName","Exists.songForm.songName");
			}
		}	
}
