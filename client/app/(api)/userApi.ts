import { AuthResponse, LoginRequest, SignUpRequest } from "@/types/api";

const BASE_URL = process.env.EXPO_PUBLIC_BASE_URL;
const AUTH_URL = `${BASE_URL}/auth`

export const signUp = async ( signUpRequest: SignUpRequest, set: any) => { 

    try{
        
        console.log("hello")

        set({
            isLoading: true,
            error: null
        })
    
        const response = await fetch(`http://<YOUR-IP>:8080/api/v1/auth/signup`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(signUpRequest)
        });

        if (!response.ok) {
            const errorData = await response.json();
            console.log(errorData)
            throw new Error(errorData.message || 'Signup failed');
        }

        const authData: AuthResponse = await response.json();
        
        console.log("hi")

        set({
            user: authData.data.user,
            token: authData.data.accessToken,
            isAuthenticated: true,
            isLoading: false,
        });

        console.log(authData);


    } catch (e) {
        set({
            isLoading: false,
            error: e instanceof Error ? e.message : 'An unknown error occurred',
        });
    }
}

export const signIn = async ( logInRequest: LoginRequest, set: any ) => {
    
    try{

        set({
            isLoading: false,
            error: null
        });

        const response = await fetch(`http://<YOUR-IP>:8080/api/v1/auth/signup`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json' 
            },
            body: JSON.stringify(logInRequest)
        });

        if(!response.ok){
            const errorData = await response.json();
            console.log(errorData);
            throw new Error(errorData.message || "SignIn failed")
        }

        const authData: AuthResponse = await response.json();

        set({
            user: authData.data.user,
            token: authData.data.accessToken,
            isLoading: false,
            isAuthenticated: true
        })
        
        console.log(authData)

    } catch (e){
        set({
            isLoading: false,
            error: e instanceof Error ? e.message : "An unkown error occurred"
        })
    }
}


export const signOut = async ( set: any ) => {
    set({
        user: null,
        isAuthenticated: false,
        token: null
    })
}
