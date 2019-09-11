package com.grauman.amdocs.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Skill {
	private String skillname;
	private int skillid;
	private SkillType type;
	
	
}
