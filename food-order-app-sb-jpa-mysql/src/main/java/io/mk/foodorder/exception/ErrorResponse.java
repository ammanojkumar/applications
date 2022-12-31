package io.mk.foodorder.exception;

public class ErrorResponse {

	private String mssage;

	public ErrorResponse(String mssage) {
		super();
		this.mssage = mssage;
	}

	public String getMssage() {
		return mssage;
	}

	public void setMssage(String mssage) {
		this.mssage = mssage;
	}

}
