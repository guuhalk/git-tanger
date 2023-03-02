package br.com.gittanger.controller;

import java.io.IOException;

import org.eclipse.jgit.api.errors.CheckoutConflictException;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRefNameException;
import org.eclipse.jgit.api.errors.RefAlreadyExistsException;
import org.eclipse.jgit.api.errors.RefNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gittanger.model.GitBranch;
import br.com.gittanger.model.GitCherryPick;
import br.com.gittanger.service.OperationCherryPickService;
import br.com.gittanger.service.OperationsService;

@RestController
@RequestMapping(value = "git")
@CrossOrigin
public class OperationsController {

	@PostMapping(value = "createBranch")
	public void createBranchDevelop(@RequestBody GitBranch gitBranch) throws IOException, RefAlreadyExistsException,
			RefNotFoundException, InvalidRefNameException, CheckoutConflictException, GitAPIException {

		OperationsService operations = new OperationsService(gitBranch);
		operations.checkoutProject(gitBranch.getBaseBranch());
		operations.pullProject(gitBranch.getUser(), gitBranch.getCredentials());
		operations.createBranch(gitBranch.getNewBranch());
		operations.checkoutProject(gitBranch.getNewBranch());
 
	}
	
	@PostMapping(value = "createCherryPick")
	public void createCherryPick(@RequestBody GitCherryPick gitCherryPick) throws IOException, RefAlreadyExistsException,
			RefNotFoundException, InvalidRefNameException, CheckoutConflictException, GitAPIException {

		OperationCherryPickService operations = new OperationCherryPickService(gitCherryPick);
		operations.checkoutProject(gitCherryPick.getOriginBranch());
		operations.cherryPickProject(gitCherryPick);

	}
	
}
