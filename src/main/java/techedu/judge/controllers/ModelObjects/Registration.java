package techedu.judge.controllers.ModelObjects;

import techedu.judge.entities.base.Secured;

public class Registration <T extends Secured> {
	public T securedItem;
	public String confirmPassword;

	Registration (T item, String pass) {
		securedItem = item;
		confirmPassword = pass;
	}

	void setConfirmPassword (String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	void setSecuredItem (T item) {
		this.securedItem = item;
	}
}
