
require 'pork'
Pork.report_at_exit

java_import 'spellbook.Hexagon'

Pork::API.describe Hexagon do
  would 'nearby' do
    Hexagon.nearby(17, 16).to_a.should.eq [ 0,  1,  2, 18, 33, 16]
    Hexagon.nearby(34, 16).to_a.should.eq [33, 18, 35, 51, 50, 49]
    Hexagon.nearby(19, 16).to_a.should.eq [ 2,  3,  4, 20, 35, 18]
    Hexagon.nearby(18, 16).to_a.should.eq [17,  2, 19, 35, 34, 33]
  end
end
