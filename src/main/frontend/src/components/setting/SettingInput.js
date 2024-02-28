import '../../css/setting/Setting.css';

const SettingInput = ( { placeholder, type } ) => {
    return(
        <div>
           <input type={type} className='settingEmailInput' placeholder={placeholder}/>
        </div>
    )
}

export default SettingInput;