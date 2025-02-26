import { View, Text, Image, Dimensions } from 'react-native'
import React from 'react'
import { OnBoardingItemType } from '@/constants/data';

type OnBoardingPropsType = {
    item: OnBoardingItemType;
}

const OnBoardingItem = ({ item }: OnBoardingPropsType) => {

    const screenWidth = Dimensions.get('window').width;

  return (
       <View 
            style={{ width: screenWidth - 32 }} // Adjust for padding
            className="items-center"
          >
            <Image 
              source={item.image!}
              className="w-full h-[40%]"
              resizeMode="contain"
            /> 
            <View className="w-full items-center mb-4">
              <Text className="text-white font-cera-pro text-2xl font-cera-bold text-center mb-2">
                {item.title}
              </Text>
              <Text className="text-gray-400 text-md font-cera-light text-center">
                {item.subtitle}
              </Text>
            </View>
          </View> 

  )
}

export default OnBoardingItem
