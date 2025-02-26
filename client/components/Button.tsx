import React from "react";
import { TouchableOpacity, Text } from "react-native";

type ButtonProps = {
  color?: string;
  borderColor?: string;
  textColor?: string;
  onPress: () => void;
  title: string;
};

const Button: React.FC<ButtonProps> = ({ color, borderColor, textColor, onPress, title }) => {
  const backgroundColor = color ? `bg-[${color}]` : "bg-transparent";
  const border = borderColor ? `border border-[#B4B4B4]/60` : "";
  const textColorClass = textColor ? `text-${textColor}` : "text-black";

  return (
    <TouchableOpacity
      className={`w-full h-14 rounded-full items-center justify-center ${backgroundColor} ${border}`}
      onPress={onPress}
    >
      <Text className={`text-lg font-cera-bold ${textColorClass}  `}>
        {title}
      </Text>
    </TouchableOpacity>
  );
};

export default Button;
