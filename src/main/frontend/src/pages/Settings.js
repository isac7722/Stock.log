import ChangePassBtn from "../components/setting/ChangePassBtn";
import SettingDisabledInput from "../components/setting/SettingDisabledInput";

const Settings = () => {

    return (
        <div className="settingDiv">
            <SettingDisabledInput
                placeholder="email"
                type='email'
           />
           <div className="passSettingDiv">
            <SettingDisabledInput
                placeholder="비밀번호"
                type='password'
           />
           <ChangePassBtn/>
           </div>

        </div>
    )
}
export default Settings;