import { View, Text, TextInput, TouchableOpacity, Alert } from 'react-native'
import React, { useState } from 'react'
import { SafeAreaView } from 'react-native-safe-area-context'
import { Feather } from '@expo/vector-icons'
import Button from '@/components/Button'
import SocialButtons from '@/components/SocialButtons'
import icons from '@/constants/icon'
import { useRouter } from 'expo-router'


const SignUp = () => {
    
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [email, setEmail] = useState("");
    const [showPassword, setShowPassword] = useState(false);
    const [showConfirmPassword, setShowConfirmPassword] = useState(false);
    const [password, setPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");
    
    const router = useRouter();

  return (
    <SafeAreaView className="flex-1 bg-black gap-3 justify-end items-center p-5" >
      <Text className='text-white mb-5 text-3xl font-cera-bold'>
        Sign Up for Wukora
      </Text>

      <View className='flex flex-row gap-5 items-center justify-between' >
        <TextInput 
          className='h-12 flex-1 text-white border-b font-cera-medium border-[#545458]/65 w-full'
          placeholder="First Name"
          placeholderTextColor="#666"
          value={firstName}
          onChangeText={setFirstName}
        />
        
        <TextInput 
          className='h-12 flex-1 text-white border-b font-cera-medium border-[#545458]/65 w-full'
          placeholder="Last Name"
          placeholderTextColor="#666"
          value={lastName}
          onChangeText={setLastName}
        />
       
      </View>

      <TextInput 
        className='h-12 text-white border-b font-cera-medium border-[#545458]/65 w-full'
        placeholder="Enter your email"
        placeholderTextColor="#666"
        value={email}
        onChangeText={setEmail}
       />
       
      <View className='w-full'>
          <TextInput
            className="h-12 border-b font-cera-medium  border-[#545458]/65 text-white pr-10"
            placeholder="Choose password"
            placeholderTextColor="#666"
            value={password}
            onChangeText={setPassword}
            secureTextEntry={!showPassword}
          />
          <TouchableOpacity className="absolute right-0 top-3" onPress={() => setShowPassword(!showPassword)}>
            <Feather name={showPassword ? "eye" : "eye-off"} size={20} color="#fff" />
          </TouchableOpacity>
      </View>
      
      <View className='w-full mb-5'>
          <TextInput
            className="h-12 border-b font-cera-medium border-[#545458]/65 text-white"
            placeholder="Confirm password"
            placeholderTextColor="#666"
            value={confirmPassword}
            onChangeText={setConfirmPassword}
            secureTextEntry={!showConfirmPassword}
          />
          <TouchableOpacity className="absolute right-0 top-3" onPress={() => setShowConfirmPassword(!showConfirmPassword)}>
            <Feather name={showConfirmPassword ? "eye" : "eye-off"} size={20} color="#fff" />
          </TouchableOpacity>
      </View>
      

      <Button 
        title="Sign Up" 
        color="#58cc02" 
        textColor="black" 
        onPress={() => router.push({
            pathname: '/domains',
            params: {
                name: `${firstName + lastName}`,
                email: email,
                password: password,
            }
        })} 
      /> 
      
      <Text className="font-cera-bold text-lg text-white">OR</Text>
       
      <View className='gap-3' > 
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

export default SignUp
