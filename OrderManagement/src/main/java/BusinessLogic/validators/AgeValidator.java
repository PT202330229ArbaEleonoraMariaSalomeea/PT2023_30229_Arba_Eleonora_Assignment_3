package BusinessLogic.validators;

import Model.Clients;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 */
public class AgeValidator implements Validator<Clients> {
	private static final int MIN_AGE = 16;
	private static final int MAX_AGE = 85;

	public void validate(Clients t) {

		if (t.getAge() < MIN_AGE || t.getAge() > MAX_AGE) {
			throw new IllegalArgumentException("The Client Age limit is not respected!");
		}

	}

}
