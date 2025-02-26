import { signUp, signIn, signOut } from "@/app/(api)/userApi";
import { LoginRequest, SignUpRequest } from "@/types/api";
import { User } from "@/types/models";
import { create } from "zustand";
import { persist, createJSONStorage } from 'zustand/middleware'
import AsyncStorage from '@react-native-async-storage/async-storage';


interface AuthState {
    user: User | null;
    token: string | null;
    isAuthenticated: boolean;
    isLoading: boolean;
    error: string | null;
    signUp: (data: SignUpRequest) => Promise<void>;
    signIn: (data: LoginRequest) => Promise<void>;
    signOut: () => void;
    clearError: () => void;
}

export const useAuthStore = create<AuthState>()(
    persist(
        (set) => ({
            user: null,
            token: null,
            isAuthenticated: false,
            isLoading: false,
            error: null,

            signUp: async (data) => signUp(data, set),
            signIn: async (data) => signIn(data, set),
            signOut: () => signOut(set),

            clearError: () => {
                set({ error: null });
            },
        }),
        {
            name: 'auth-storage',
            storage: createJSONStorage(() => AsyncStorage),

        }
    )
)

