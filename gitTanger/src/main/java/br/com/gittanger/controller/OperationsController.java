package br.com.gittanger.controller;

import java.io.IOException;

import org.eclipse.jgit.api.errors.CheckoutConflictException;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRefNameException;
import org.eclipse.jgit.api.errors.RefAlreadyExistsException;
import org.eclipse.jgit.api.errors.RefNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gittanger.model.GitTanger;
import br.com.gittanger.service.OperationsService;

@RestController
@RequestMapping(value = "git")
public class OperationsController {

	@PostMapping(value = "createBranch")
	public void createBranchDevelop(@RequestBody GitTanger gitTanger) throws IOException, RefAlreadyExistsException,
			RefNotFoundException, InvalidRefNameException, CheckoutConflictException, GitAPIException {

		OperationsService operations = new OperationsService(gitTanger);
		operations.CheckoutProject(gitTanger.getBaseBranch());
		operations.pullProject(gitTanger.getUser(), gitTanger.getCredentials());
		operations.createBranch(gitTanger.getNewBranch());
		operations.CheckoutProject(gitTanger.getNewBranch());
 
	}
}
