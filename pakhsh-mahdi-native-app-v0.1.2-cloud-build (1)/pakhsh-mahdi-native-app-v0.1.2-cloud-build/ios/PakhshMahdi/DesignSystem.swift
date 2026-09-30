import SwiftUI

enum PMColor {
    static let primary = Color(hex: 0x01082B)
    static let secondary = Color(hex: 0x07012B)
    static let surface = Color.white
    static let border = Color(hex: 0xD2D2D4)
    static let background = Color(hex: 0xF2F2F2)
    static let success = Color(hex: 0x16A34A)
}

extension Color {
    init(hex: UInt, alpha: Double = 1) {
        self.init(.sRGB,
                  red: Double((hex >> 16) & 0xff) / 255,
                  green: Double((hex >> 08) & 0xff) / 255,
                  blue: Double((hex >> 00) & 0xff) / 255,
                  opacity: alpha)
    }
}
