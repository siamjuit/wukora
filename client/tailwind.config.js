/** @type {import('tailwindcss').Config} */
module.exports = {
    content: ["./app/**/*.{js,jsx,ts,tsx}", "./components/**/*.{js,jsx,ts,tsx}"],
    presets: [require("nativewind/preset")],
    theme: {
        extend: {
            fontFamily: {
                "cera": ["CeraPro-Regular", "sans-serif"],
                "cera-bold": ["CeraPro-Bold", "sans-serif"],
                "cera-medium": ["CeraPro-Medium", "sans-serif"],
                "cera-semibold": ["CeraPro-SemiBold", "sans-serif"],
                "cera-light": ["CeraPro-Regular", "sans-serif"], // Using Regular as Light since you're loading it as Regular
                "cera-black": ["CeraPro-SemiBold", "sans-serif"], // Using SemiBold as Black since you're loading it as Black
            },
           
        },
    },
    plugins: [],
};
