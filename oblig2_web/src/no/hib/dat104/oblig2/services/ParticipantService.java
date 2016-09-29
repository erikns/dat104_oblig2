package no.hib.dat104.oblig2.services;

import no.hib.dat104.oblig2.models.ParticipantEntity;
import no.hib.dat104.oblig2.models.ParticipantPublicViewModel;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class ParticipantService {
    @PersistenceContext(name = "partyDb")
    private EntityManager em;

    /**
     * Sign a participant up for the party
     * @param participant Participant to sign up for the party
     */
    public void signup(ParticipantEntity participant) {
        em.persist(participant);
    }

    /**
     * Get all participants represented by ParticipantPublicViewModel instances.
     * @return The list of participants
     */
    public List<ParticipantPublicViewModel> getParticipantsPublic() {
        List<ParticipantPublicViewModel> result = new ArrayList<>();

        Query query = em.createQuery("SELECT p FROM ParticipantEntity p ORDER BY p.firstName, p.lastName");
        List queryResult = query.getResultList();

        for (Object p : queryResult) {
            ParticipantEntity participant = (ParticipantEntity) p;
            result.add(new ParticipantPublicViewModel(participant));
        }

        return result;
    }

    /**
     * Get all participants represented directly by the entity classes. This is intended to be used
     * only by the admin part of the application
     * @return List of participants
     */
    public List<ParticipantEntity> getAllParticipants() {
        Query query = em.createQuery("SELECT p FROM ParticipantEntity p");
        List queryResult = query.getResultList();

        List<ParticipantEntity> resultList = new ArrayList<>();
        for (Object p : queryResult)  {
            ParticipantEntity participant = (ParticipantEntity) p;
            resultList.add(participant);
        }

        return resultList;
    }

    /**
     * Return a single participant. Intended to be used only by admin parts of the application.
     * @param phone The participant phone number
     * @return The participant entity
     */
    public ParticipantEntity getParticipant(String phone) {
        return em.find(ParticipantEntity.class, phone);
    }

    /**
     * Register payment for single participant. Intended to be used only by admin parts of the application.
     * @param participantPhone The participant phone number (id) to register payment for
     */
    public void registerPayment(String participantPhone) {
        ParticipantEntity participant = getParticipant(participantPhone);
        participant.setPaid(true);

        em.merge(participant);
    }
}
