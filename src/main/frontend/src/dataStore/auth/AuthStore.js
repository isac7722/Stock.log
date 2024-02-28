import { create } from "zustand";

export const useSignupStore = create((set)=>({
    email:'',
    setEmail:(value) => set({email:value}),
    password:'',
    setPassword:(value)=>set({password:value}),
}));