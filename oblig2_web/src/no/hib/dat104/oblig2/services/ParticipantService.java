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

    public void signup(ParticipantEntity participant) {
        em.persist(participant);
    }

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

    public ParticipantEntity getParticipant(String phone) {
        return em.find(ParticipantEntity.class, phone);
    }
}
