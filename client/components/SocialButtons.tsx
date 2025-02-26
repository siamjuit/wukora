import { View, Text, Image, TouchableOpacity } from 'react-native'
import React from 'react'

type SocialButtonProps = {
    icon: string;
    onPress: () => void;
    title: string
}


const SocialButtons = ({ icon, title, onPress }: SocialButtonProps) => {
  return (
    <TouchableOpacity 
        onPress={onPress}
        className='flex flex-row h-14 p-3 rounded-full border border-[#B4B4B4]/60 w-full justify-between items-center' >
      <Image source={icon} className='h-8 w-8' />
      <Text className='text-white text-lg font-cera-bold' >{title}</Text>
      <View/>
    </TouchableOpacity>
  )
}

export default SocialButtons
