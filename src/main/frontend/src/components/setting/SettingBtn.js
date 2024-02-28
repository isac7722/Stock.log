import { useState } from 'react';
import '../../css/setting/Setting.css';
import Button from "react-bootstrap/Button";
import { Modal } from 'react-bootstrap';
import ModalInput from './ModalInput';

const SettingBtn = ( { title , modalTitle } ) => {

    /* 모달 활성화 여부 */
    const [show, setShow] = useState(false);
    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    // 비밀번호 변경에 사용
    const [password, setPassword] = useState('');

    const passwordOnchangeHandler = (e) => {
        setPassword(e.target.value);
    }

    const [checkPassword, setCheckPassword] = useState('');

    const checkPasswordOnchangeHandler = (e) => {
        setCheckPassword(e.target.value);
    }

    // 금액 변경할 때 사용 
    const [amount, setAmount] = useState('');
    const amountOnChangeHandler = (e) => {
        setAmount(e.target.value);
    }

    return(
        <>
        <Button id='modalBtn' onClick={handleShow}>{title}</Button>

        <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>{modalTitle}</Modal.Title>
        </Modal.Header>
        <Modal.Body>
        {title === "비밀번호 변경" && ( // 비밀번호 변경 버튼 조건부 렌더링
            <>
              <ModalInput
                placeholder="변경할 비밀번호를 입력해주세요"
                type="password"
                onChange={passwordOnchangeHandler}
                value={password}
              />
              <ModalInput
                placeholder="변경할 비밀번호를 다시 입력해주세요"
                type="password"
                onChange={checkPasswordOnchangeHandler}
                value={checkPassword}
              />
              <Modal.Footer>
            <Button id='modalBtn' onClick={handleClose}>
                비밀번호 변경
            </Button>
            <Button id='modalBtn' onClick={handleClose}>
                취소
            </Button>
        </Modal.Footer>
            </>
          )}
           {title === "입금/출금" && ( // 입금/출금 버튼 조건부 렌더링
            <>
              <ModalInput
                placeholder="변경할 금액을 입력해주세요"
                type="number"
                onChange={amountOnChangeHandler}
                value={amount}
              />
            <Modal.Footer>
                <Button id='modalBtn' onClick={handleClose}>
                    입금
                </Button>
                <Button id='modalBtn' onClick={handleClose}>
                    출금
                </Button>
                <Button id='modalBtn' onClick={handleClose}>
                    취소
                </Button>
        </Modal.Footer>
            </>
          )}
            
            {title === "데이터 초기화" && ( // 데이터 초기화 버튼 조건부 렌더링
            <>
                <p>정말 데이터를 초기화 하시겠습니까?</p>
            <Modal.Footer>
                <Button id='modalBtn' onClick={handleClose}>
                    예
                </Button>
                <Button id='modalBtn' onClick={handleClose}>
                    아니요
                </Button>
        </Modal.Footer>
            </>
          )}

        {title === "회원 탈퇴" && ( // 회원 탈퇴 조건부 렌더링
            <>
                <p>정말 회원 탈퇴 하시겠습니까?</p>
            <Modal.Footer>
                <Button id='modalBtn' onClick={handleClose}>
                    예
                </Button>
                <Button id='modalBtn' onClick={handleClose}>
                    아니요
                </Button>
        </Modal.Footer>
            </>
          )}
        </Modal.Body>
      </Modal>
        </>
    )
}

export default SettingBtn;