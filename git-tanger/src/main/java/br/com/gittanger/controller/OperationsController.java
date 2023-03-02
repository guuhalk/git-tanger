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
	public String createBranchDevelop(@RequestBody GitBranch gitBranch) throws IOException, RefAlreadyExistsException,
			RefNotFoundException, InvalidRefNameException, CheckoutConflictException, GitAPIException {

		String returnMessage = "";
		StringBuilder sb = new StringBuilder();
		
		OperationsService operations = new OperationsService(gitBranch);
		
		returnMessage =  operations.checkoutProject(gitBranch.getBaseBranch());
		sb.append(returnMessage).append("\n");
		returnMessage = operations.pullProject(gitBranch.getUser(), gitBranch.getCredentials());
		sb.append(returnMessage).append("\n");
		returnMessage = operations.createBranch(gitBranch.getNewBranch());
		sb.append(returnMessage).append("\n");
		returnMessage = operations.checkoutProject(gitBranch.getNewBranch());
		sb.append(returnMessage).append("\n");
		
		return sb.toString();
 
	}
	
	@PostMapping(value = "createCherryPick")
	public String createCherryPick(@RequestBody GitCherryPick gitCherryPick) throws IOException, RefAlreadyExistsException,
			RefNotFoundException, InvalidRefNameException, CheckoutConflictException, GitAPIException {

		String returnMessage = "";
		StringBuilder sb = new StringBuilder();
		
		OperationCherryPickService operations = new OperationCherryPickService(gitCherryPick);
		
		operations.checkoutProject(gitCherryPick.getOriginBranch());
		sb.append(returnMessage).append("\n");
		
		operations.cherryPickProject(gitCherryPick);
		sb.append(returnMessage).append("\n");
		
		return returnMessage;

	}
	
}
