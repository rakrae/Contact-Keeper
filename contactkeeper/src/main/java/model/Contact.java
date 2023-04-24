package model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@NamedQuery(name = "readAllContacts", query = "select c from Contact c")
@Table(name = "contacts")
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;
	
	private String gender;

	private String birthday;
	
	private String address;

	private String phoneNumber;

	private String email;

	private String facebook;

	private String linkedIn;

	private String instagram;
	
	@Column(name = "comment")
	private String comment;
	
    @OneToOne(targetEntity = Photo.class, mappedBy = "contact", cascade = CascadeType.ALL, orphanRemoval = true)
    private Photo photo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_id", nullable = false)
	private Account account;

	public Contact() {
	}

	public Contact(String firstName, String lastName,String gender, String birthday, String address,
			String phoneNumber, String email, String facebook, String linkedIn, String instagram, String comment) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.birthday = birthday;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.facebook = facebook;
		this.linkedIn = linkedIn;
		this.instagram = instagram;
		this.comment = comment;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String string) {
		this.phoneNumber = string;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getLinkedIn() {
		return linkedIn;
	}

	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}

	public String getInstagram() {
		return instagram;
	}

	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}
	
	public void addPhoto(byte[] photoData, String filename) {
        this.photo = new Photo(photoData, filename);
        this.photo.setContact(this);
    }

    public void deletePhoto() {
        if (this.photo != null) {
            this.photo.setContact(null);
            this.photo = null;
        }
    }

    public void updatePhoto(byte[] photoData, String filename) {
        if (this.photo != null) {
            this.photo.setPhotoData(photoData);
            this.photo.setFilename(filename);
        } else {
            addPhoto(photoData, filename);
        }
    }

	@Override
	public String toString() {
		return "Contact [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender
				+ ", birthday=" + birthday + ", address=" + address + ", phoneNumber=" + phoneNumber + ", email="
				+ email + ", facebook=" + facebook + ", linkedIn=" + linkedIn + ", instagram=" + instagram
				+ ", comment=" + comment + ", photo=" + photo + ", account=" + account + "]";
	}

}
