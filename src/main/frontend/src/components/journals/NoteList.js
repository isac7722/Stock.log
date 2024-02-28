import Carousel from "react-bootstrap/Carousel";
import {Col, Row} from "react-bootstrap";
import Card from "react-bootstrap/Card";


const NoteList = ({noteList}) => {

    // 카드 리스트를 3개씩 묶어서 표시하기 위해 조정
    const groupedNotes = [];
    while (noteList.length > 0) {
        groupedNotes.push(noteList.splice(0, 3));
    }

    // Carousel 영역
    const notesMapping = groupedNotes.map((group, index) => (
        <Carousel.Item key={index}>
            <Row>
                {group.map((note, index) => (
                    <Col key={index}>
                        <Card>
                            <Card.Body>
                                {note.noteContents}
                            </Card.Body>
                        </Card>
                    </Col>
                ))}
            </Row>
        </Carousel.Item>
    ));

    return (
        <>
            <Carousel>
                {notesMapping}
            </Carousel>
        </>
    )
}

export default NoteList;