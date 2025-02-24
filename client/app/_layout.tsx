import { GestureHandlerRootView } from 'react-native-gesture-handler';
import '../global.css';
import { useFonts } from 'expo-font'
import { SplashScreen, Stack } from 'expo-router';
import { useEffect } from 'react';

const RootLayout = () => {

    const [ fontsLoaded ] = useFonts({
        "CeraPro-Bold": require("../assets/fonts/Cera-Pro-Bold.ttf"),
        "CeraPro-Medium": require("../assets/fonts/Cera-Pro-Medium.ttf"),
        "CeraPro-Regular": require("../assets/fonts/Cera-Pro-Light.ttf"),
        "CeraPro-SemiBold": require("../assets/fonts/Cera-Pro-Black.ttf"),
    });

    useEffect(() => {
        if( fontsLoaded ){
            SplashScreen.hideAsync();
        }
    }, [fontsLoaded])


    return (
        <GestureHandlerRootView>
            <Stack screenOptions={{
                headerShown: false
            }}/>
        </GestureHandlerRootView>
    )
}

export default RootLayout;
