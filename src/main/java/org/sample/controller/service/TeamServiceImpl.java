package org.sample.controller.service;

import java.util.Date;

import org.sample.controller.exceptions.InvalidUserException;
import org.sample.controller.pojos.CreateTeam;
import org.sample.model.Team;
import org.sample.model.dao.TeamDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
public class TeamServiceImpl implements TeamService{
	
	@Autowired    TeamDao teamDao;
	
	@Transactional
	public CreateTeam createTeam(CreateTeam createTeam)throws InvalidUserException {
		String teamName = createTeam.getName();
		if (StringUtils.isEmpty(teamName)) {
			throw new InvalidUserException("Team name has to be entered");
		}
		
		
		Team team = new Team();
		team.setName(teamName);
		team.setDate(new Date());
		team = teamDao.save(team);
		createTeam.setId(team.getId());
		
		return createTeam;
	}
	
	public Iterable<Team> getTeams() {
		return teamDao.findAll();
	}
}
