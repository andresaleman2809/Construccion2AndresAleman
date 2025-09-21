package app.adapter.in.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.adapter.in.validators.PetValidator;
import app.adapter.in.validators.UserValidator;
import app.domain.model.Pet;
import app.domain.model.User;

/**
 *
 * @author ESTUDIANTE
 */
@Component
public class PetBuilder {
	@Autowired
	private PetValidator petValidator;
	@Autowired
	private UserValidator userValidator;

	public Pet builder(String document, String name, String age, String weigth, String spices, String features,
			String breed) throws Exception {
		Pet pet = new Pet();
		User owner = new User();
		owner.setDocument(userValidator.documentValidator(document));
		pet.setOwner(owner);
		pet.setAge(petValidator.ageValidator(age));
		pet.setBreed(petValidator.breedValidator(breed));
		pet.setFeatures(petValidator.featuresValidator(features));
		pet.setWeigth(petValidator.weigthValidator(weigth));
		pet.setSpices(petValidator.spicesValidator(spices));
		pet.setName(petValidator.nameValidator(name));
		return pet;
	}

}