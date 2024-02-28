import '../../css/setting/Setting.css';

const SettingDisabledInput = ( { placeholder, type } ) => {
    return(
        <div>
           <input type={type} className='settingEmailInput' placeholder={placeholder} disabled/>
        </div>
    )
}

export default SettingDisabledInput;