import {create} from "zustand";
import {callJournalList} from "../apis/JournalsAPI";

// zustand 사용 = 초기값과 그 값을 변환하는 메소드를 담아둬서 전역에서 어디든지 사용 가능
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