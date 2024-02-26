import React from 'react';

const AuthButton = ({ onClick, buttonText }) => {
  return (
    <button onClick={onClick} className='authBtn'>
      {buttonText}
    </button>
  );
};

export default AuthButton;
