package com.tonearena.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.tonearena.beans.Song;

@Component
public class SongFormValidator implements Validator{
		
		@Override
		public boolean supports(Class<?> clazz) {
			return Song.class.equals(clazz);
		}

		@Override
		public void validate(Object target, Errors errors) {

			Song song = (Song) target;

			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "songName", "NotEmpty.songForm.songName");

			if(song.getSongName().length() > 50){
				errors.rejectValue("songName","Valid.songForm.songName");
			}
		}	
}
