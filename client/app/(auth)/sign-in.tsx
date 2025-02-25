import { View, Text, TextInput, TouchableOpacity } from 'react-native'
import React, { useState } from 'react'
import { SafeAreaView } from 'react-native-safe-area-context'
import { Feather } from '@expo/vector-icons'
import Button from '@/components/Button'
import SocialButtons from '@/components/SocialButtons'
import icons from '@/constants/icon'

const SignIn = () => {

    const [email, setEmail] = useState("");
    const [showPassword, setShowPassword] = useState(false);
    const [password, setPassword] = useState("");

  return (
    <SafeAreaView className="flex-1 gap-5 bg-black justify-end items-center p-5" >
      <Text className='text-white text-3xl font-cera-bold'>
        Log in to Wukora
      </Text>


      <TextInput 
        className='h-12 text-white border-b font-cera-medium border-[#545458]/65 w-full'
        placeholder="Enter your email"
        placeholderTextColor="#666"
        value={email}
        onChangeText={setEmail}
      />
      
      <View className='w-full'>
          <TextInput
            className="h-12 border-b border-[#545458]/65 text-white pr-10 font-cera-medium"
            placeholder="Password"
            placeholderTextColor="#666"
            value={password}
            onChangeText={setPassword}
            secureTextEntry={!showPassword}
          />
          <TouchableOpacity className="absolute right-0 top-3" onPress={() => setShowPassword(!showPassword)}>
            <Feather name={showPassword ? "eye" : "eye-off"} size={20} color="#fff" />
          </TouchableOpacity>
      </View>
      
      <TouchableOpacity>
          <Text className="text-white/50 text-center font-cera-light underline">Forgot password?</Text>
      </TouchableOpacity>
        
      <Button 
        title="Log In" 
        color="#58cc02" 
        textColor="black" 
        onPress={() => console.log("Sign Up Pressed")} 
      /> 
      
      <Text className="font-cera-bold text-lg text-white">OR</Text>
       
      <View className='gap-4' > 
      <SocialButtons 
        icon={icons.Google}
        title="Continue with Google"
        onPress={() => console.log("hi")}
      />
      <SocialButtons
        icon={icons.GitHub}
        title="Continue with Github"
        onPress={() => console.log("hi")}
      />
    </View>
    </SafeAreaView>
  )
}

export default SignIn
