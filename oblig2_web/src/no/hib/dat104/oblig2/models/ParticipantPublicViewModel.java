package no.hib.dat104.oblig2.models;


public class ParticipantPublicViewModel {
    private String firstName;
    private String lastName;
    private String gender;
    private boolean paid;
    private String phone;

    public ParticipantPublicViewModel(ParticipantEntity participantEntity) {
        this.firstName = participantEntity.getFirstName();
        this.lastName = participantEntity.getLastName();
        this.gender = participantEntity.getGender();
        this.phone = participantEntity.getPhone();
        this.paid = participantEntity.isPaid();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public boolean isUser(String user) {
        return phone.equals(user);
    }

    public boolean isPaid() {
        return paid;
    }
}
