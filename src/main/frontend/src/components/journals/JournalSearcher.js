import {useState} from "react";
import Form from 'react-bootstrap/Form';
import Button from "react-bootstrap/Button";
import {callJournalListBySearch} from "../../apis/JournalsAPI";


const JournalSearcher = ({journalList, setJournalList}) => {

    const [text, setText] = useState("");

    const searcherHandler = (e) => {
        setText(e.target.value);
    };

    const onSubmitText = (e) => {
        e.preventDefault();
        setJournalList(callJournalListBySearch(text));
    };

    return (
        <>
            <Form onSubmit={onSubmitText}>
                <Form.Control
                    type={"text"}
                    id={"search"}
                    value={text}
                    onChange={searcherHandler}
                />
                <Button type={"submit"} variant={"primary"}>검색</Button>
            </Form>
        </>
    )
}

export default JournalSearcher;