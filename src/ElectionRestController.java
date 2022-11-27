package president.election.application.controllers;


import president.election.application.models.Candidate;
import president.election.application.models.CandidateVotes;
import president.election.application.models.RegionVotes;
import president.election.application.services.CandidateService;
import president.election.application.services.VoteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
public class ElectionRestController {
    /**
     * @Autowired Services to get data
     */
    @Autowired
    private CandidateService candidateService;
    @Autowired
    private  VoteService voteService;

    /**
     *
     * @param candidateService
     * @param voteService
     * Rest controller's constructor.
     */
    public ElectionRestController(CandidateService candidateService, VoteService voteService) {
        this.candidateService = candidateService;
        this.voteService = voteService;
    }

    /**
     *
     * @param person_id
     * @param List_number
     * @return String that tells us about vote status.
     * @throws SQLException
     */
    @RequestMapping(value = "/vote/{person_id}/{List_number}", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> postNewVote(@PathVariable("person_id") int person_id, @PathVariable("List_number") int List_number) throws SQLException {

        return Collections.singletonMap("response",voteService.createVote(person_id,List_number));
    }

    /**
     *
     * @return List of all candidates.
     * @throws JsonProcessingException
     */
    @RequestMapping(value = "/getCandidates", method = RequestMethod.GET,  produces = "application/json")
    public List<Candidate> getCandidates () throws JsonProcessingException {

        return candidateService.findAll();
    }

    /**
     *
     * @return Number of votes for each candidate.
     * @throws SQLException
     */
    @RequestMapping(value = "/getVotesPerCandidate", method = RequestMethod.GET,  produces = "application/json")
    public List<CandidateVotes> getCandidateVotes () throws SQLException {
        return voteService.getVotesPerCandidate();
    }

    /**
     *
     * @return Number of votes in each region that voted.
     * @throws SQLException
     */
    @RequestMapping(value = "/getRegionVotes", method = RequestMethod.GET,  produces = "application/json")
    public List<RegionVotes> getRegionVotes () throws SQLException {
        return voteService.regionVotes();
    }

    /**
     *
     * @return Candidate or 2 candidates that had most votes.
     * @throws SQLException
     */
    @RequestMapping(value = "/getWinners", method = RequestMethod.GET,  produces = "application/json")
    public List<Candidate> getWinners () throws SQLException {
        return candidateService.getWinningCandidates();
    }



}
