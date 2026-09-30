import SwiftUI

struct CartView: View {
    @EnvironmentObject private var cart: CartStore

    var body: some View {
        VStack {
            if cart.lines.isEmpty {
                ContentUnavailableView("سبد خرید خالی است", systemImage: "cart", description: Text("محصولات موردنظر را به سبد اضافه کنید."))
            } else {
                List {
                    ForEach(cart.lines) { line in
                        HStack {
                            Button("−") { cart.decrement(line.id) }
                            Text("\(line.quantity)")
                            Button("+") { cart.increment(line.id) }
                            Spacer()
                            VStack(alignment: .trailing) { Text(line.product.name).fontWeight(.semibold); Text(toman(line.product.price)).foregroundStyle(PMColor.primary) }
                        }
                    }
                }
                Button("ادامه و ثبت سفارش") {}
                    .frame(maxWidth: .infinity).padding().background(PMColor.primary).foregroundStyle(.white).clipShape(RoundedRectangle(cornerRadius: 16)).padding()
            }
        }
        .navigationTitle("سبد خرید")
    }
}
