import React, { useState, useRef } from 'react';
import { View, Text, TouchableOpacity, ScrollView, Dimensions, NativeSyntheticEvent, NativeScrollEvent } from 'react-native';
import { OnBoardingItems } from '@/constants/data';
import OnBoardingItem from '@/components/OnBoardingItem';
import { Link, Redirect, router } from 'expo-router';
import Button from '@/components/Button'

const OnboardingScreen = () =>  {
  const [activeSlide, setActiveSlide] = useState(0);
  const scrollViewRef = useRef<ScrollView>(null);
  const screenWidth = Dimensions.get('window').width;

  const handleScroll = (event: NativeSyntheticEvent<NativeScrollEvent>) => {
    const contentOffset = event.nativeEvent.contentOffset.x;
    const currentIndex = Math.round(contentOffset / (screenWidth - 32)); 
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
    <View className="flex-1 bg-black p-4">
      <Text className="text-white text-3xl font-cera-bold text-center">
        Welcome to Wukora!
      </Text>
      

      <ScrollView
        ref={scrollViewRef}
        horizontal
        pagingEnabled
        showsHorizontalScrollIndicator={false}
        onMomentumScrollEnd={handleScroll}
        contentContainerClassName="items-start mt-10"
      >
        {OnBoardingItems.map((item, index) => ( 
            <OnBoardingItem key={index} item={item}/> 
        ))}
      </ScrollView>

      <View className="items-center my-auto justify-center">
        <View className="flex-row mb-5">
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
         
        <Button 
          title="Sign Up" 
          color="#58cc02"
          textColor="black" 
          onPress={() => router.replace('/sign-up')} 
        />
        <Button 
          title="Log In" 
          borderColor="gray-400"
          textColor="white" 
          onPress={() => router.replace('/sign-in')} 
        />
        </View>
      </View>
    </View>
  );
}

export default OnboardingScreen;
