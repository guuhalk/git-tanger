package br.com.gittanger.model;

import lombok.Data;

@Data
public class GitCherryPick {
	private String originBranch;
	private String baseBranch;
	private String path;
}
