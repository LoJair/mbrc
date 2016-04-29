package com.kelsos.mbrc.events.ui

import com.kelsos.mbrc.annotations.SettingsAction
import com.kelsos.mbrc.domain.DeviceSettings

class ChangeSettings(@SettingsAction.Action val action: Int, val settings: DeviceSettings)