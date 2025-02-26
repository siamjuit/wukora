import Button from '@/components/Button';
import { useAuthStore } from '@/store/authStore';
import { Skill } from '@/types/models';
import { useState } from 'react';
import { View, Text, Pressable, ScrollView, Alert, Modal, Platform, ActivityIndicator } from 'react-native';
import { BlurView } from 'expo-blur'
import { useLocalSearchParams } from 'expo-router';


interface Domain {
  skill: Skill
  selected?: boolean;
}
const domains: Domain[] = [
  { skill: { id: "web", name: "Web Development", icon: "🌐" } },
  { skill: { id: "app", name: "App Development", icon: "📱" } },
  { skill: { id: "ai", name: "Artificial Intelligence", icon: "🧠" } },
  { skill: { id: "ml", name: "Machine Learning", icon: "📊" } },
  { skill: { id: "ux", name: "User Interface and Experience", icon: "🎨" } },
  { skill: { id: "web3", name: "WEB 3.0", icon: "🖥️" } },
  { skill: { id: "security", name: "Cybersecurity", icon: "🔒" } },
  { skill: { id: "data", name: "Data Science", icon: "👨‍🔬" } },
  { skill: { id: "product", name: "Product Management", icon: "📦" } },
  { skill: { id: "robotics", name: "Robotics", icon: "🤖" } },
  { skill: { id: "iot", name: "Internet of Things", icon: "🌐" } },
  { skill: { id: "cloud", name: "Cloud Computing", icon: "☁️" } },
  { skill: { id: "quantum", name: "Quantum Computing", icon: "💻" } },
  { skill: { id: "blockchain", name: "Blockchain", icon: "⛓️" } },
  { skill: { id: "hardware", name: "Hardware", icon: "🖨️" } },
  { skill: { id: "marketing", name: "Social Media Marketing", icon: "📱" } },
  { skill: { id: "game", name: "Game Development", icon: "🎮" } },
  { skill: { id: "finance", name: "Finance", icon: "💰" } },
  { skill: { id: "3d", name: "Animation and 3D Modeling", icon: "🦄" } }
];

const DomainsScreen = () => {
  const [selectedDomains, setSelectedDomains] = useState<Skill[]>([]);
  
  const {name , email, password} = useLocalSearchParams<{ name: string, email: string, password: string }>();


  const toggleDomain = (skill: Skill) => {
    setSelectedDomains((prev) => {
      const isSelected = prev.some(domain => domain.id === skill.id);
      if (isSelected) {
        return prev.filter(domain => domain.id !== skill.id);
      } else {
        return [...prev, skill];
      }
    });
  };


  const { signUp, isLoading, error } = useAuthStore();

  const handleSignUp = async () => {
    try {
      await signUp({ name, email, password });
      Alert.alert('Success', 'Account created successfully!');
    } catch (err) {

        console.log(err)

      Alert.alert('Signup Failed', error || 'Something went wrong!');
    }
  }

  if(isLoading){
        return (
            <Modal transparent animationType="fade" visible={isLoading}>
                <View className="absolute inset-0 flex justify-center items-center bg-black/30">
                    {/* Use platform-specific blur implementation */}
                    {Platform.OS === 'ios' ? (
                        <BlurView className="absolute inset-0" intensity={10}/>
                    ) : (
                        <BlurView className="absolute inset-0" intensity={50}/>
                )}

                
                <ActivityIndicator size="large" color="yellow" />
            </View>
          </Modal>
        );
  }

  return (
    <View className="flex-1 bg-black">
      {/* Progress bar */}
      <View className="w-full h-1 bg-[#dee0e7]/20">
        <View className="h-full w-[50%] bg-[#6afa00]" />
      </View>

      <View className="px-4 py-12 flex-1">
        <Text className="text-4xl font-cera-bold text-white text-center mb-4">
          Domains
        </Text>
        <Text className="text-[#bfc3cf] font-cera-light text-center text-lg mb-12">
          Let everyone know what domains you're interested in by adding them to your profile.
        </Text>

        <ScrollView 
          className="flex-1 mb-8" 
          showsVerticalScrollIndicator={false}
        >
          <View className="flex-row flex-wrap justify-center gap-2">
            {domains.map((domain) => (
              <Pressable
                key={domain.skill.id}
                onPress={() => toggleDomain(domain.skill)}
                className={`
                  flex-row transition delay-80 items-center p-1 border rounded-full border-2
                  ${
                    selectedDomains.includes(domain.skill)
                      ? "bg-[#193901] border-[#6AFA00]"
                      : "border-[#dee0e7]/20 bg-black/40"
                  }
                `}
              >
                <Text className="mr-2">{domain.skill.icon}</Text>
                <Text
                  className={
                    `${selectedDomains.includes(domain.skill)
                      ? "text-[#6AFA00]"
                      : "text-white"} font-cera-bold `
                  }
                >
                  {domain.skill.name}
                </Text>
              </Pressable>
            ))}
          </View>
        </ScrollView>
        <Button 
            title="Continue"
            color="#58cc02"
            onPress={handleSignUp}
        />
      </View>
    </View>
  );
}

export default DomainsScreen
