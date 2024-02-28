import {create} from "zustand";
import {callJournalList} from "../apis/JournalsAPI";


const useJournalStore = create(set => ({
    journalList: [],
    setJournalList: async () => {
        try {
            const data = await callJournalList(); // 프로미스를 해결하기 위해 await 사용
            set({ journalList: data }); // 상태를 업데이트
        } catch (error) {
            console.error(error);
        }
    }
    }));

export default useJournalStore;