package no.hib.dat104.oblig2.models;


public class ParticipantPublicViewModel {
    private String firstName;
    private String lastName;
    private String gender;

    public ParticipantPublicViewModel(ParticipantEntity participantEntity) {
        this.firstName = participantEntity.getFirstName();
        this.lastName = participantEntity.getLastName();
        this.gender = participantEntity.getGender();
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
}
