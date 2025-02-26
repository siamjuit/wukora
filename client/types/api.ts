import { Skill, User } from '@/types/models'

export type SignUpRequest = {
    name: string;
    email: string;
    password: string;
    
}

export type LoginRequest = {
    email: string;
    password: string;
}

export type AuthResponse = {
    message: string;
    data: JwtResponse
}

export type JwtResponse = {
    accessToken: string;
    refreshToken: string;
    user: User
}
