export type User = {
  id: string;
  email: string;
  name: string;
  roles: string[];
  skills: Set<Skill>;
  appliedTeams: Set<Team>;
  gitHubUrl: string;
  portfolioUrl: string;
  bio: string;
  lookingForTeam: boolean;
  profileUrl: string;
  bannerUrl: string;
}

export type Team = {
  id: string;
  name: string;
  description: string;
  requiredSkills: Set<Skill>;
  maxMembers: number;
  teamMembers: Set<User>;
  hackathonsApplied: Set<Hackathon>;
}

export type Hackathon = {
  id: string;  
  name: string;
  description: string;
  hackathonDate: string;
  theme: HackathonTheme;
  lastDateToRegister: string;
  postedOn: string;
  startDate: string;
  endDate: string;
  teamsApplied: Team[];
  prizes: string[];
}

export type HackathonTheme = {
  id: string;
  name: string;
}

export type Skill = {
    id: string;
    name: string;
    icon: string;
}
