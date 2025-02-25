import images from '@/constants/image'


export type OnBoardingItemType = {
    image: string;
    title: string;
    subtitle: string
}



export const OnBoardingItems: Array<OnBoardingItemType> = [
    {
        image: images.onboarding1,
        title: "Find Your Perfect\nHackathon Teammate",
        subtitle: "No more last-minute team struggles\nWukora makes it easy!",
    },
    {
        image: images.onboarding2,
        title: "Smart Matching\nFaster Connections",
        subtitle: "Swipe right to connect with potential\nteammates and start building!",
    },
    {
        image: images.onboarding3,
        title: "Match and\nCollaborate Instantly",
        subtitle: "Once you have found your match, connect and\nget ready to win your next hackathon together!",
    }
]
