package no.hib.dat104.oblig2;

import no.hib.dat104.oblig2.models.ParticipantEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ParticipantService {
    @PersistenceContext(name = "partyDb")
    private EntityManager em;

    public void signup(ParticipantEntity participant) {
        em.persist(participant);
    }
}
