package com.dev.hroauth.entities.enums;

public enum RoleList {
	
	ADMIN(1, "ROLE_ADMIN"),
	USER(2, "ROLE_USER");
	
	private int cod;
	private String descricao;
	
	private RoleList(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static RoleList toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for (RoleList x : RoleList.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + cod);
	}

}
