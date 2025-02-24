import React, { useState, useRef } from 'react';
import { View, Text, Image, TouchableOpacity, ScrollView, Dimensions, NativeSyntheticEvent, NativeScrollEvent } from 'react-native';
import images from '@/constants/image';
import { OnBoardingItems } from '@/constants/data';



export default function OnboardingScreen() {
  const [activeSlide, setActiveSlide] = useState(0);
  const scrollViewRef = useRef(null);
  const screenWidth = Dimensions.get('window').width;

  const handleScroll = (event: NativeSyntheticEvent<NativeScrollEvent>) => {
    const contentOffset = event.nativeEvent.contentOffset.x;
    const currentIndex = Math.round(contentOffset / (screenWidth - 32)); // Adjust for padding
    setActiveSlide(currentIndex);
  };

  const scrollToIndex = (index: number): void => {
    scrollViewRef.current?.scrollTo({
      x: index * (screenWidth - 32), // Adjust for padding
      animated: true
    });
    setActiveSlide(index);
  };

  return (
    <View className="flex-1 bg-black p-4 justify-content-center">
      <Text className="text-white text-3xl font-cera-bold text-center pt-5">
        Welcome to Wukora!
      </Text>

      <ScrollView
        ref={scrollViewRef}
        horizontal
        pagingEnabled
        showsHorizontalScrollIndicator={false}
        onMomentumScrollEnd={handleScroll}
        contentContainerClassName="items-center"
      >
        {OnBoardingItems.map((item, index) => (
          <View 
            key={index}
            style={{ width: screenWidth - 32 }} // Adjust for padding
            className="items-center"
          >
            <Image 
              source={item.image}
              className="w-full h-[40%] mb-10"
              resizeMode="contain"
            /> 
            <View className="w-full items-center mb-8">
              <Text className="text-white font-cera-pro text-2xl font-cera-bold text-center mb-2">
                {item.title}
              </Text>
              <Text className="text-gray-400 text-md font-cera-light text-center">
                {item.subtitle}
              </Text>
            </View>
          </View>
        ))}
      </ScrollView>

      <View className="items-center">
        <View className="flex-row mb-20">
          {OnBoardingItems.map((_, index) => (
            <TouchableOpacity 
              key={index}
              onPress={() => scrollToIndex(index)}
            >
              <View 
                className={`w-2 h-2 rounded-full mx-1 ${
                  index === activeSlide ? 'bg-[#58cc02]' : 'bg-[#d9d9d9]'
                }`}
              />
            </TouchableOpacity>
          ))}
        </View>

        <View className="w-full gap-4">
          <TouchableOpacity 
            className="w-full h-14 bg-[#58cc02] rounded-full items-center justify-center"
          >
            <Text className="text-black text-lg font-cera-bold ">
              Sign Up
            </Text>
          </TouchableOpacity>
          
          <TouchableOpacity 
            className="w-full h-14 border border-gray-400 rounded-full items-center justify-center"
          >
            <Text className="text-white text-lg font-cera-bold">
              Log In
            </Text>
          </TouchableOpacity>
        </View>
      </View>
    </View>
  );
}
