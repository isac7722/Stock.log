import {create} from "zustand";
import {callJournalList} from "../apis/JournalsAPI";


const useJournalStore = create(set => ({
    journalList: [],
    setJournalList: () => set(state => ({journalList: async () => {
            const response = await callJournalList()
        }})
    )
}))

export default useJournalStore;