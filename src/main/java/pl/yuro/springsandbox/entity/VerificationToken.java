package pl.yuro.springsandbox.entity;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="verificaton_token")
@NoArgsConstructor
@EqualsAndHashCode
@Setter
@Getter
public class VerificationToken {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	
	private String token;
	
	@Column(name="expiry_date")
	private Timestamp expireTime;
	
	@OneToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn( name="user_id", referencedColumnName = "user_id")
	private User user;

	public VerificationToken(String token, User user) {
		this.token = token;
		this.user = user;
	}
	
	
	
	
	

}
