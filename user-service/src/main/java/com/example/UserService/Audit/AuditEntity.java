package com.example.UserService.Audit;

import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="revinfo_custom")
@RevisionEntity(AuditListener.class)
public class AuditEntity extends DefaultRevisionEntity {
	
	private static final long serialVersionUID = 1L;
	
	public String username;
	public String ip;
}